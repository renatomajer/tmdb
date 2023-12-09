package agency.five.tmdb.data.database.mapper

interface EntityMapper<S, E> {

    fun toEntity(data: S): E

    fun toEntities(data: Collection<S>): List<E> {
        return data.map { toEntity(it) }
    }

    fun fromEntity(entity: E): S

    fun fromEntities(entities: Collection<E>): List<S> {
        return entities.map { fromEntity(it) }
    }
}