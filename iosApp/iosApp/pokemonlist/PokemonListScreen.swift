import SwiftUI

struct PokemonListScreen: View {
    
    @StateObject var pokemonsViewModel = PokemonsViewModel()
    
    var body: some View {
        switch pokemonsViewModel.uiState {
        case .loading(let isLoading):
            if (isLoading) { Text("Loading") }
        case .pokemonListItems(let pokemons):
            ListPokemon(pokemons: pokemons)
        case .error(let errorMessage):
            Text(errorMessage)
        }
    }
}

struct PokemonListScreen_Previews: PreviewProvider {
	static var previews: some View {
        PokemonListScreen()
	}
}

