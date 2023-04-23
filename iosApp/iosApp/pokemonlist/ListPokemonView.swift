import SwiftUI
import shared

struct ListPokemonView: View {
    
    @State private var searchText = ""
    private let pokemons: [PokemonListItem]
    private var filteredPokemons: [PokemonListItem] {
        if (searchText.isEmpty) {
            return pokemons
        } else {
            return pokemons.filter{ $0.doesMatchSearch(query: searchText) }
        }
    }
    
    init(pokemons: [PokemonListItem]) {
        self.pokemons = pokemons
    }
    
    var body: some View {
        NavigationView {
            List(filteredPokemons, id: \.number) { pokemon in
                NavigationLink {
                    PokemonDetailScreen(pokemonNumber: pokemon.number)
                } label: {
                    HStack {
                        AsyncImage(url: URL(string: pokemon.imageUrl), content: { image in
                            image.resizable().aspectRatio(contentMode: .fit)
                        }, placeholder: {
                            Image(systemName: "photo.fill")
                        })
                        Text(String(format: "%03d", pokemon.number)).padding().font(.title3)
                        Text(pokemon.name).font(.title3)
                    }.frame(width: nil, height: 70)
                }
            }
            .searchable(text: $searchText)
            .navigationTitle("Pokédex")
        }
    }
}
