package com.ertugrulakkaya.spaceexplorer.domain.usecase

interface UseCase<in P, out R> {
    suspend operator fun invoke(params: P): R
}

interface NoParamsUseCase<out R> {
    suspend operator fun invoke(): R
}

interface SynchronousUseCase<in P, out R> {
    fun execute(params: P): R
}