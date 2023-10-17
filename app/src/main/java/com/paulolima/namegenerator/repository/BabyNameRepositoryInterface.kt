package com.paulolima.namegenerator.repository

import com.paulolima.namegenerator.domain.model.BabyNameItem

interface BabyNameRepositoryInterface {

    suspend fun getDatabase(): List<BabyNameItem>
}