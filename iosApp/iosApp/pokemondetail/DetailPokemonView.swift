import SwiftUI
import shared

struct DetailPokemonView: View {
    
    let pokemon: Pokemon
    
    var body: some View {
        Text(pokemon.name)
    }
}
