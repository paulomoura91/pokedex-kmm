import SwiftUI

struct PokemonDetailScreen: View {
    
    private let pokemonNumber: Int32
    @StateObject private var pokemonDetailViewModel: PokemonDetailViewModel
    
    init(pokemonNumber: Int32) {
        self.pokemonNumber = pokemonNumber
        _pokemonDetailViewModel = StateObject(wrappedValue: PokemonDetailViewModel(pokemonNumber: pokemonNumber))
    }
    
    var body: some View {
        switch pokemonDetailViewModel.uiState {
        case .loading(let isLoading):
            if (isLoading) { ProgressView() }
        case .pokemon(let pokemon):
            DetailPokemon(pokemon: pokemon)
        case .error(let errorMessage):
            ErrorView(errorMessage: errorMessage)
        }
    }
}

struct PokemonDetailScreen_Previews: PreviewProvider {
    static var previews: some View {
        PokemonDetailScreen(pokemonNumber: 1)
    }
}
