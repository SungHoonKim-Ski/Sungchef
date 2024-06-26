package com.ssafy.sungchef.data.datasource.user

import android.util.Log
import com.google.gson.Gson
import com.ssafy.sungchef.commons.DataState
import com.ssafy.sungchef.commons.SERVER_INSTABILITY
import com.ssafy.sungchef.data.api.UserService
import com.ssafy.sungchef.data.datasource.BaseRemoteDataSource
import com.ssafy.sungchef.data.local.dao.BookmarkDao
import com.ssafy.sungchef.data.model.APIError
import com.ssafy.sungchef.data.model.entity.BookmarkEntity

import com.ssafy.sungchef.data.model.requestdto.BookMarkRequest
import com.ssafy.sungchef.data.model.requestdto.ContactRequestDTO

import com.ssafy.sungchef.data.model.requestdto.SurveyRequestDTO
import com.ssafy.sungchef.data.model.requestdto.UserRequestDTO
import com.ssafy.sungchef.data.model.requestdto.UserSnsIdRequestDTO
import com.ssafy.sungchef.data.model.requestdto.UserUpdateRequestDTO

import com.ssafy.sungchef.data.model.responsedto.BookmarkRecipeList
import com.ssafy.sungchef.data.model.responsedto.MakeRecipeList
import com.ssafy.sungchef.data.model.responsedto.ResponseDto
import com.ssafy.sungchef.data.model.responsedto.UserSettingInfo
import com.ssafy.sungchef.data.model.responsedto.UserSimple
import com.ssafy.sungchef.data.model.responsedto.survey.SurveyResponse
import com.ssafy.sungchef.data.model.responsedto.token.TokenResponse
import com.ssafy.sungchef.data.model.responsedto.user.LoginResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "UserDataSourceImpl_성식당"
class UserDataSourceImpl @Inject constructor(
    private val userService : UserService,
    private val bookmarkDataSource: BookmarkDataSource
) : UserDataSource, BaseRemoteDataSource(){
    override suspend fun duplicateNickname(nickname: String): DataState<APIError> {
        return try {
            getResult {
                userService.duplicateNickname(nickname)
            }
        } catch (e : Exception){
            Log.d(TAG, "duplicateNickname: ${e.message}")
            DataState.Error(APIError(500, ""))
        }
    }


    override suspend fun userSimple(): UserSimple {
        return userService.userSimple();
    }

    override suspend fun makeRecipeList(page : Int) : MakeRecipeList {
        return userService.makeRecipeList(page)
    }

    override suspend fun bookmarkRecipeList(page : Int) : BookmarkRecipeList {
        return userService.bookmarkRecipeList(page)
    }

    override suspend fun changeBookmarkRecipe(bookMarkRequest: BookMarkRequest): DataState<APIError> {
        if (bookMarkRequest.isBookmark) {
            bookmarkDataSource.insert(BookmarkEntity(bookMarkRequest.recipeId))
        } else {
            bookmarkDataSource.delete(BookmarkEntity(bookMarkRequest.recipeId))
        }
        return getResult { userService.changeBookmarkRecipe(bookMarkRequest) }
    }


    override suspend fun surveySubmit(surveyRequestDTO: SurveyRequestDTO): DataState<APIError> {
        return try {
            getResult {
                userService.submitSurvey(surveyRequestDTO)
            }
        } catch (e : Exception) {
            Log.d(TAG, "surveySubmit: 설문 제출 실패, ${e.message}")
            DataState.Error(APIError(500, ""))
        }
    }

    override suspend fun getSurvey(): DataState<ResponseDto<SurveyResponse>> {
        return try {
            getResult {
                userService.getSurvey()
            }
        } catch (e : Exception) {
            DataState.Error(APIError(500, SERVER_INSTABILITY))
        }
    }

    override suspend fun userSettingInfo(): UserSettingInfo {
        return userService.userSettingInfo()
    }

    override suspend fun signupUser(userRequestDTO: UserRequestDTO): DataState<ResponseDto<TokenResponse>> {
        return getResult {
            userService.signupUser(userRequestDTO)
        }
    }

    override suspend fun login(userSnsIdRequestDTO: UserSnsIdRequestDTO): DataState<ResponseDto<LoginResponse>> {
        return getResult {
            userService.login(userSnsIdRequestDTO)
        }
    }
    override suspend fun inquire(contactRequestDTO: ContactRequestDTO) : Response<APIError> {
        return userService.userContact(contactRequestDTO)
    }
    override suspend fun updateUserInfo(userUpdateRequestDTO: UserUpdateRequestDTO) : Response<APIError> {

        val map = HashMap<String, RequestBody>()
        val nickname = RequestBody.create("application/json".toMediaTypeOrNull(), userUpdateRequestDTO.userNickName)
        val gender = RequestBody.create("application/json".toMediaTypeOrNull(), userUpdateRequestDTO.userGender.toString())
        val birthDate = RequestBody.create("application/json".toMediaTypeOrNull(), userUpdateRequestDTO.userBirthDate)
        Log.d(TAG, "updateUserInfo: ${userUpdateRequestDTO.userImage}")
        map["userNickName"] = nickname
        map["userGender"] = gender
        map["userBirthdate"] = birthDate

        return userService.updateUserInfo(userUpdateRequestDTO.userImage, map)
    }

    override suspend fun autoLogin(): DataState<APIError> {
        return getResult {
            userService.autoLogin()
        }
    }
}