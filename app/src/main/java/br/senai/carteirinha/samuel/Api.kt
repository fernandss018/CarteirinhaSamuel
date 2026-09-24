package br.senai.carteirinha.samuel

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class LoginRequest(val login: String, val senha: String)
data class LoginResponse(
    val id: String,
    val nome: String,
    val matricula: String,
    val curso: String,
    val turma: String,
    val token: String
)
data class UnidadeCurricular(
    val id: String,
    val nome: String,
    val professor: String,
    val nota1: Double,
    val nota2: Double,
    val media: Double,
    val faltas: Int
)

interface CarteirinhaApi {
    @POST("auth/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse

    @GET("unidades-curriculares")
    suspend fun listarUcs(): List<UnidadeCurricular>
}

class ApiTraceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d("API_TRACE", "REQUEST ${request.method} ${request.url}")
        val response = try {
            chain.proceed(request)
        } catch (e: Exception) {
            Log.e("API_TRACE", "ERROR ${request.method} ${request.url}: ${e.message}")
            throw e
        }
        Log.d("API_TRACE", "RESPONSE ${response.code} ${request.method} ${request.url}")
        return response
    }
}

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val logging = HttpLoggingInterceptor { message ->
        Log.d("API_HTTP", message)
    }.apply { level = HttpLoggingInterceptor.Level.BASIC }

    private val client = OkHttpClient.Builder()
        .addInterceptor(ApiTraceInterceptor())
        .addInterceptor(logging)
        .addInterceptor { chain ->
            val token = Session.token
            val request = if (!token.isNullOrBlank() && chain.request().url.encodedPath != "/auth/login") {
                chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else chain.request()
            chain.proceed(request)
        }
        .build()

    val service: CarteirinhaApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CarteirinhaApi::class.java)
}

object Session {
    var token: String? = null
    var user: LoginResponse? = null
}
