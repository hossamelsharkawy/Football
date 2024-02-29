package com.hossam.football.features.competition.domain

class GetCompetitionListUseCase(
    private val competitionsRepository: CompetitionsRepository,
) {
    suspend operator fun invoke() = competitionsRepository.getCompetitions()
}
