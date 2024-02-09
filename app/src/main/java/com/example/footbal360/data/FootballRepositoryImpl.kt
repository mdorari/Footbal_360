package com.example.footbal360.data


import com.example.footbal360.data.model.chips.Chips
import com.example.footbal360.data.model.sections.AllPosts
import com.example.footbal360.data.model.story.Stories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class FootballRepositoryImpl(
    private val api: FootballApi
) : FootballRepository {
//    override suspend fun getAllPosts(): Flow<Result<AllPosts>> {
//        return flow {
//            val postsFromApi = try {
//                api.getAllPosts()
//            } catch (e: IOException) {
//                e.printStackTrace()
//                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
//                return@flow
//            } catch (e: HttpException) {
//                e.printStackTrace()
//                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
//                return@flow
//            }
//            emit(Result.Success(postsFromApi))
//        }
//    }

//    override suspend fun getPostsBySection(key: String): Flow<Result<AllPosts>> {
//        return flow {
//            val postsBySectionFromApi = try {
//                api.getPostsBySection(key = key)
//            } catch (e: IOException) {
//                e.printStackTrace()
//                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
//                return@flow
//            } catch (e: HttpException) {
//                e.printStackTrace()
//                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
//                return@flow
//            }
//            emit(Result.Success(postsBySectionFromApi))
//        }
//    }

    override suspend fun getSliderPosts(): Flow<Result<AllPosts>> {
        return flow {
            val explorerPostsFromApi = try {
                api.getSliderPosts()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
                return@flow
            }
            emit(Result.Success(explorerPostsFromApi))
        }
    }

    override suspend fun getStories(): Flow<Result<Stories>> {
        return flow {
            val storiesFromApi = try{
                api.getStories()
            }catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
                return@flow
            }
            emit(Result.Success(storiesFromApi))
        }
    }

    override suspend fun getYourChips(): Flow<Result<Chips>> {
        return flow {
            val chipsFromApi = try {
                api.getYoursChips()
            }catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
                return@flow
            }
            emit(Result.Success(chipsFromApi))
        }
    }
}