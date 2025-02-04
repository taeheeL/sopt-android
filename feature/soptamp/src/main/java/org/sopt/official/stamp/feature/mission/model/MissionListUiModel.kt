/*
 * Copyright 2022-2023 SOPT - Shout Our Passion Together
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
package org.sopt.official.stamp.feature.mission.model

import org.sopt.official.stamp.domain.MissionLevel
import org.sopt.official.stamp.domain.model.Mission

data class MissionListUiModel(
    val title: String,
    val missionList: List<MissionUiModel>
)

// TODO: filter 부분은 나중에 삭제하기
fun List<Mission>.toUiModel(title: String): MissionListUiModel = MissionListUiModel(
    title = title,
    missionList = this
        .filter { it.level <= 3 }
        .map { it.toUiModel() }
)

fun Mission.toUiModel(): MissionUiModel = MissionUiModel(
    id = this.id,
    title = this.title,
    level = MissionLevel.of(this.level),
    isCompleted = this.isCompleted
)
