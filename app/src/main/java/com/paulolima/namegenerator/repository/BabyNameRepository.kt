package com.paulolima.namegenerator.repository

import com.paulolima.namegenerator.domain.model.BabyNameItem
import com.paulolima.namegenerator.network.BabyNameService
import javax.inject.Inject

class BabyNameRepository @Inject constructor(
    private val babyNameService: BabyNameService,
) : BabyNameRepositoryInterface {

    override suspend fun getDatabase(): List<BabyNameItem> {
        babyNameService.getDataset().let { babyList ->
            return babyList.map {
                BabyNameItem(
                    yearOfBirth = it[0],
                    gender = it[1],
                    ethnicity = it[2],
                    name = it[3],
                    amount = it[4],
                    rank = it[5],
                )
            }
        }
    }
}