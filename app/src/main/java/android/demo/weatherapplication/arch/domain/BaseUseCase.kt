package android.demo.weatherapplication.arch.domain

abstract class BaseUseCase<R : BaseUseCase.UseCaseRequest, P : BaseUseCase.UseCaseResponse>() {

    abstract suspend fun executeUseCase(request:R?) : DomainDataWrapper<P>

    interface UseCaseRequest
    interface UseCaseResponse

}