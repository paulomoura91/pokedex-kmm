import shared
import Combine
import KMPNativeCoroutinesCombine

class PokemonListViewModel : ObservableObject {
    
    @Published var uiState = PokemonListState.loading(false)
    private var cancellable: AnyCancellable? = nil
    
    init() {
        getPokemons()
    }
    
    private func getPokemons() {
        let useCase = UseCasesHelper().getPokemonsUseCase
        let publisher = createPublisher(for: useCase.getPokemons())
        cancellable = publisher.receive(on: DispatchQueue.main).sink { completion in
            print("Received completion: \(completion)")
        } receiveValue: { value in
            switch(value) {
            case is ResponseLoading<NSArray>:
                self.uiState = PokemonListState.loading(true)
            case is ResponseSuccess<NSArray>:
                self.uiState = PokemonListState.pokemonListItems(value.data as? [PokemonListItem] ?? [])
            case is ResponseError<NSArray>:
                self.uiState = PokemonListState.error(ErrorConstants().ERROR_POKEMON_LIST_STATE)
            default:
                break
            }
        }
    }
}
