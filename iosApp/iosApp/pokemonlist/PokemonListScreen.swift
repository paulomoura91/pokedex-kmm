import SwiftUI

struct ContentView: View {
    
    @StateObject var pokemonsViewModel = PokemonsViewModel()
    
	var body: some View {
        List(pokemonsViewModel.pokemons, id: \.number) { pokemon in
            Text(pokemon.name)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}