import SwiftUI
import shared

struct DetailPokemon: View {
    
    let pokemon: Pokemon
    
    var body: some View {
        Text(pokemon.name)
    }
}
