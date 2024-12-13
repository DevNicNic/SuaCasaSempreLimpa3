package com.nicnicdev.suacasasemprelimpa03.data

import android.content.Context
import com.nicnicdev.suacasasemprelimpa03.domain.model.UserRegistration
import java.lang.Exception

class RegistrationRepository(context: Context) {
    private val userDao = DatabaseProvider.getDatabase(context).userDao()

   suspend fun register(user: UserRegistration): Result<Unit> {
        return try {
            val userEntity = UserEntity(
                name = user.name,
                email = user.email,
                password = user.password
            )
            userDao.insertUser(userEntity)  //esse é o ponto onde os dados são salvos no banco
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // método para verificar login
    suspend fun login (email: String, password: String): Result<UserEntity> {
        return try {
            val user = userDao.getUser (email, password)
            if (user != null) Result.success(user) else Result.failure(Exception("Usuário não encontrado "))
        } catch (e:Exception){
            Result.failure(e)
        }

    }
}