import SwiftUI
import shared

struct DetailPokemonView: View {
    
    let pokemon: Pokemon
    
    var body: some View {
        VStack {
            Text(pokemon.name).font(.title)
            Text("Nº \(String(format: "%03d", pokemon.number))" ).font(.title2)
            AsyncImage(url: URL(string: pokemon.imageUrl), content: { image in
                image
                    .resizable()
                    .frame(width: 250, height: 250)
            }, placeholder: {
                Image(systemName: "photo.fill")
            })
            Text(pokemon.description_)
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.bottom, 15)
            Text("Type")
                .frame(maxWidth: .infinity, alignment: .leading)
                .font(.title2)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding(.all, 10)
    }
}
