package android.watch_movie.network.entity

import com.google.gson.annotations.Expose

data class BudgetNetworkEntity(
    @Expose
    val grossRu: Int? = null,
    @Expose
    val grossUsa: Int? = null,
    @Expose
    val grossWorld: Int? = null,
    @Expose
    val budget: String? = null,
    @Expose
    val marketing: Int? = null,
)