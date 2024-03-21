package com.ssafy.sungchef.data.repository

import com.ssafy.sungchef.data.datasource.user.UserDataSource
import com.ssafy.sungchef.data.model.APIError
import com.ssafy.sungchef.domain.repository.UserRepository
import retrofit2.Response

import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository{
    override suspend fun duplicateNickname(nickname: String): Flow<DataState<BaseModel>> {
        return flow {
            val isDuplicate = userDataSource.duplicateNickname(nickname)

            if (isDuplicate is DataState.Success) {
                emit(DataState.Success(isDuplicate.data.toBaseModel()))
            }
        }
    }

    override suspend fun userSimple(): UserSimple {
        return userDataSource.userSimple();
    }

    override suspend fun makeRecipeList(page : Int) : MakeRecipeList{
        return userDataSource.makeRecipeList(page)
    }
}