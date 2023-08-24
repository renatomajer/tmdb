package agency.five.tmdb


data class Actor(
    val name: String,
    val surname: String,
    val movie: String,
    val role: String = "", // needs to be changed
    val imageResId: Int = -1, // id from drawable folder (R.drawable...) -> needs to be changed
)


data class PersonFunction(
    val name: String,
    val surname: String,
    val movieFunction: String
)