import SwiftUI

struct PokemonListScreen: View {
    
    @StateObject var pokemonListViewModel = PokemonListViewModel()
    
    var body: some View {
        switch pokemonListViewModel.uiState {
        case .loading(let isLoading):
            if (isLoading) { ProgressView() }
        case .pokemonListItems(let pokemons):
            ListPokemon(pokemons: pokemons)
        case .error(let errorMessage):
            ErrorView(errorMessage: errorMessage)
        }
    }
}

struct PokemonListScreen_Previews: PreviewProvider {
	static var previews: some View {
        PokemonListScreen()
	}
}

