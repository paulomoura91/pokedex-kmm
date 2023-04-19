import SwiftUI
import shared

struct ListPokemon: View {
    
    private let pokemons: [PokemonListItem]
    
    init(pokemons: [PokemonListItem]) {
        self.pokemons = pokemons
    }
    
    var body: some View {
        List(pokemons, id: \.number) { pokemon in
            Text(pokemon.name)
        }
    }
}
