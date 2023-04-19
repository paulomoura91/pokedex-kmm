import SwiftUI
import shared

struct ListPokemon: View {
    
    private let pokemons: [PokemonListItem]
    
    init(pokemons: [PokemonListItem]) {
        self.pokemons = pokemons
    }
    
    var body: some View {
        List(pokemons, id: \.number) { pokemon in
            HStack {
                AsyncImage(url: URL(string: pokemon.imageUrl), content: { image in
                    image.resizable().aspectRatio(contentMode: .fit)
                }, placeholder: {
                    Image(systemName: "photo.fill")
                })
                Text(String(pokemon.number)).padding().font(.title3)
                Text(pokemon.name).font(.title3)
            }.frame(width: nil, height: 70)
        }
    }
}
