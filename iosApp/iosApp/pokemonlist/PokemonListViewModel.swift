import shared
import RxSwift
import KMPNativeCoroutinesRxSwift

class PokemonListViewModel : ObservableObject {
    
    @Published var uiState = PokemonListState.loading(false)
    
    init() {
        getPokemons()
    }
    
    private func getPokemons() {
        let useCase = UseCasesHelper().getPokemonsUseCase
        let observable = createObservable(for: useCase.getPokemons())
        let _ = observable
            .observe(on: MainScheduler.instance)
            .subscribe(onNext: { value in
                switch(value) {
                case is ResponseLoading<NSArray>:
                    self.uiState = PokemonListState.loading(true)
                case is ResponseSuccess<NSArray>:
                    self.uiState = PokemonListState.pokemonListItems(value.data as! [PokemonListItem])
                case is ResponseError<NSArray>:
                    self.uiState = PokemonListState.error(ErrorConstants().ERROR_POKEMON_LIST_STATE)
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
