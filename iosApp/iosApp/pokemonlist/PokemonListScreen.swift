import SwiftUI

struct ContentView: View {
    
    @StateObject var pokemonsViewModel = PokemonsViewModel()
    
    var body: some View {
        switch pokemonsViewModel.uiState {
        case .loading(let isLoading):
            if (isLoading) { Text("Loading") }
        case .pokemonListItems(let pokemons):
            List(pokemons, id: \.number) { pokemon in
                Text(pokemon.name)
            }
        case .error(let errorMessage):
            Text(errorMessage)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
