/*
 * Copyright 2023 SOPT - Shout Our Passion Together
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sopt.official.stamp.feature.ranking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.official.stamp.R
import org.sopt.official.stamp.designsystem.component.button.SoptampIconButton
import org.sopt.official.stamp.designsystem.component.util.noRippleClickable
import org.sopt.official.stamp.designsystem.style.SoptTheme
import org.sopt.official.stamp.feature.ranking.model.RankerUiModel
import org.sopt.official.stamp.feature.ranking.model.TopRankerDescriptionBubble

@Composable
fun TopRankerList(
    topRanker: Triple<RankerUiModel, RankerUiModel, RankerUiModel>,
    onClickTopRankerBubble: (RankerUiModel) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                bottom = 10.dp,
                start = 9.dp,
                end = 9.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var onClickRankerState by remember {
            mutableStateOf(topRanker.first)
        }
        if (onClickRankerState.rank > 0) {
            TopRankDescriptionBubble(
                bubble = TopRankerDescriptionBubble.findBubbleByRank(onClickRankerState.rank),
                onClickRankerDescriptionState = onClickRankerState.getDescription(),
                onClickItem = { onClickTopRankerBubble(onClickRankerState) }
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                20.dp,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.Bottom
        ) {
            val onClickRanker = { ranker: RankerUiModel ->
                onClickRankerState = ranker
            }
            TopRankerItem(ranker = topRanker.second, 110.dp, onClickRanker)
            TopRankerItem(ranker = topRanker.first, 150.dp, onClickRanker)
            TopRankerItem(ranker = topRanker.third, 70.dp, onClickRanker)
        }
    }
}

@Composable
fun TopRankDescriptionBubble(
    bubble: TopRankerDescriptionBubble,
    onClickRankerDescriptionState: String,
    onClickItem: () -> Unit = {}
) {
    Box(
        modifier = Modifier.noRippleClickable {
            onClickItem()
        }
    ) {
        Icon(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = bubble.background),
            contentDescription = "Top Ranker DescriptionBubble",
            tint = bubble.backgroundColor
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 3.dp,
                    bottom = 13.dp,
                    start = 24.dp,
                    end = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            val maxLength = 19
            val description = if (onClickRankerDescriptionState.length > maxLength) {
                "${onClickRankerDescriptionState.substring(0 until maxLength)}..."
            } else {
                onClickRankerDescriptionState
            }
            Text(
                text = description,
                style = SoptTheme.typography.sub3,
                color = bubble.textColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
            SoptampIconButton(
                imageVector = ImageVector.vectorResource(id = R.drawable.right_forward),
                tint = Color.White,
                onClick = { onClickItem() }
            )
        }
    }
}

@Preview
@Composable
fun PreviewTopRankerList() {
    SoptTheme {
        TopRankerList(
            topRanker = Triple(
                RankerUiModel(
                    rank = 1,
                    nickname = "jinsu",
                    description = "일이삼사육칠팔구십일이삼사오육칠팔구십dlfdl",
                    score = 1000

                ),
                RankerUiModel(
                    rank = 2,
                    nickname = "jinsu",
                    score = 900
                ),
                RankerUiModel(
                    rank = 3,
                    nickname = "jinsu",
                    score = 800
                )
            )
        )
    }
}
