package agency.five.tmdb.ui.state

import agency.five.tmdb.domain.common.Credits

data class CreditsUiState(
        val isLoading: Boolean = false,
        val credits: Credits? = null,
        val error: String? = null
)