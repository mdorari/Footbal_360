package com.example.footbal360.data


import com.example.footbal360.data.model.sections.AllPosts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class FootballRepositoryImpl(
    private val api: FootballApi
) : FootballRepository {
    override suspend fun getAllPosts(): Flow<Result<AllPosts>> {
        return flow {
            val postsFromApi = try {
                api.getAllPosts()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
                return@flow
            }
            emit(Result.Success(postsFromApi))
        }
    }

    override suspend fun getPostsBySection(key: String): Flow<Result<AllPosts>> {
        return flow {
            val postsBySectionFromApi = try {
                api.getPostsBySection(key = key)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error loading ducks, error type ${e.message}"))
                return@flow
            }
            emit(Result.Success(postsBySectionFromApi))
        }
    }

    override suspend fun getExplorerPosts(): Flow<Result<AllPosts>> {
        return flow {
            val explorerPostsFromApi = try {
                api.getExplorerPosts()
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
}