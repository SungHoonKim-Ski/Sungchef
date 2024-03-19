package com.ssafy.sungchef.features.screen.signup

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssafy.sungchef.commons.INPUT_BIRTH
import com.ssafy.sungchef.commons.INPUT_GENDER
import com.ssafy.sungchef.commons.NEXT_STEP
import com.ssafy.sungchef.features.component.FilledButtonComponent
import com.ssafy.sungchef.features.component.TextComponent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SignupGenderScreen(

) {
    Scaffold (
        topBar = {
            SignupTopBar()
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box {
                Column(
                    modifier = Modifier
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 40.dp
                        )
                ) {
                    TextComponent(
                        text = INPUT_GENDER,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )

                    // TODO 남성 여성 화면 그리기

                    SignupBirth(
                        isClickable = false,
                        disabledBorderColor = Color.LightGray
                    )
                    SignupNickname(false)

                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                }
                FilledButtonComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter), // Box 내에서 하단 중앙 정렬
                    text = NEXT_STEP
                ) {
                    // TODO navigation으로 screen 이동
                    // TODO 화면 넘길 때 Topbar 숫자 배경 바꾸기
                    // TODO ViewModel에 생년월일 저장
                    // TODO 뒤로 가기 구현 (onBackPressed 포함)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun SignupGenderPreview(){
    SignupGenderScreen()
}