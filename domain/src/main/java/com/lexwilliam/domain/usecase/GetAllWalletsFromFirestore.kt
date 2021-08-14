package com.lexwilliam.domain.usecase

import com.lexwilliam.local.repository.WalletRepository
import javax.inject.Inject

class GetAllWalletsFromFirestore
@Inject constructor(
    private val walletRepository: WalletRepository
) {
    suspend operator fun invoke() = walletRepository.getWalletsFromFirestore()
}