import shared
import RxSwift
import KMPNativeCoroutinesRxSwift

class PokemonDetailViewModel : ObservableObject {
    
    @Published var uiState = PokemonDetailState.loading(false)
    
    init(pokemonNumber: Int32) {
        getPokemon(pokemonNumber: pokemonNumber)
    }
    
    private func getPokemon(pokemonNumber: Int32) {
        let useCase = UseCasesHelper().getPokemonUseCase
        let observable = createObservable(for: useCase.getPokemon(number: pokemonNumber))
        let _ = observable
            .observe(on: MainScheduler.instance)
            .subscribe(onNext: { value in
                switch(value) {
                case is ResponseLoading<Pokemon>:
                    self.uiState = PokemonDetailState.loading(true)
                case is ResponseSuccess<Pokemon>:
                    if let pokemon = value.data {
                        self.uiState = PokemonDetailState.pokemon(pokemon)
                    } else {
                        self.uiState = PokemonDetailState.error(ErrorConstants().ERROR_POKEMON_DETAIL_STATE)
                    }
                case is ResponseError<Pokemon>:
                    self.uiState = PokemonDetailState.error(ErrorConstants().ERROR_POKEMON_DETAIL_STATE)
                default:
                    break
                }
            }, onError: { error in
                print("Received error: \(error)")
            }, onCompleted: {
                print("Observable completed")
            }, onDisposed: {
                print("Observable disposed")
            })
    }
}
