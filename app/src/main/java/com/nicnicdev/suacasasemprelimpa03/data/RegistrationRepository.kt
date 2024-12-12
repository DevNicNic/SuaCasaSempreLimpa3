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
            userDao.insertUser(userEntity)  //esse é o ponto onde os dados são sslvos no banco
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}