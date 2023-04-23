import shared
import Combine
import KMPNativeCoroutinesCombine

class PokemonDetailViewModel : ObservableObject {
    
    @Published var uiState = PokemonDetailState.loading(false)
    private var cancellable: AnyCancellable? = nil
    
    init(pokemonNumber: Int32) {
        getPokemon(pokemonNumber: pokemonNumber)
    }
    
    private func getPokemon(pokemonNumber: Int32) {
        let useCase = UseCasesHelper().getPokemonUseCase
        let publisher = createPublisher(for: useCase.getPokemon(number: pokemonNumber))
        cancellable = publisher.receive(on: DispatchQueue.main).sink { completion in
            print("Received completion: \(completion)")
        } receiveValue: { value in
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
        }
    }
}
