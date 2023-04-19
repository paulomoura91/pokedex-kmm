import shared
import RxSwift
import KMPNativeCoroutinesRxSwift

class PokemonsViewModel : ObservableObject {
    
    @Published var uiState = PokemonListState.loading(false)
    private let useCase: GetPokemonsUseCase
    
    init(useCase: GetPokemonsUseCase = UseCasesHelper().getPokemonsUseCase) {
        
        self.useCase = useCase
        getPokemons()
    }
    
    private func getPokemons() {
        
        let observable = createObservable(for: useCase.getPokemons())
        
        let disposable = observable
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
