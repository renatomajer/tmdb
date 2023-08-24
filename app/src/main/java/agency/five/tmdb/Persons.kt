package agency.five.tmdb

import agency.five.tmdb.ui.components.MovieItemViewState
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Actor(
    val name: String,
   // val surname: String,
    //val movie: String,
    val character: String = "", // needs to be changed
    val imageResId: Int = -1, // id from drawable folder (R.drawable...) -> needs to be changed
    val profile_path: String?
)

@Serializable
data class ActorResponse(
    @SerialName("cast")
    val actors: List<Actor>
)


@Serializable
data class PersonFunction(
    val name: String,
    val department: String
)

@Serializable
data class PersonsFunctionsResponse(
    @SerialName("crew")
    val personsFunctions: List<PersonFunction>
)