import SwiftUI
import shared

struct DetailPokemonView: View {
    
    let pokemon: Pokemon
    
    var body: some View {
        ScrollView {
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
                HStack {
                    ForEach(pokemon.types, id: \.self) { type in
                        Text(type.uppercased())
                            .frame(width: 120)
                            .padding(10)
                            .font(.system(size: 15))
                            .background(getTypeColor(type: type))
                            .cornerRadius(40)
                            .padding(5)
                    }
                }
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.bottom, 15)
                Text("Evolutions")
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .font(.title2)
                if (pokemon.evolutions.count == 1) {
                    Text("This Pokémon does not evolve.")
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .padding(.top, 10)
                        .padding(.bottom, 10)
                }
                ForEach(pokemon.evolutions, id: \.number) { evolution in
                    AsyncImage(url: URL(string: evolution.imageUrl), content: { image in
                        image
                            .resizable()
                            .frame(width: 115, height: 115)
                    }, placeholder: {
                        Image(systemName: "photo.fill")
                    })
                    Text(evolution.name)
                    Text("Nº \(String(format: "%03d", evolution.number))" )
                }
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
            .padding(.all, 10)
        }
    }
    
    private func getTypeColor(type: String) -> Color {
        switch(type.lowercased()) {
        case PokemonType.grass.rawValue: return Color.init(hex: "008000")
        case PokemonType.poison.rawValue: return Color.init(hex: "800080")
        case PokemonType.fire.rawValue: return Color.init(hex: "FF0000")
        case PokemonType.flying.rawValue: return Color.init(hex: "6495ED")
        case PokemonType.water.rawValue: return Color.init(hex: "00BFFF")
        case PokemonType.bug.rawValue: return Color.init(hex: "9ACD32")
        case PokemonType.normal.rawValue: return Color.init(hex: "778899")
        case PokemonType.electric.rawValue: return Color.init(hex: "FFD700")
        case PokemonType.ground.rawValue: return Color.init(hex: "DEB887")
        case PokemonType.fairy.rawValue: return Color.init(hex: "FF69B4")
        case PokemonType.fighting.rawValue: return Color.init(hex: "A52A2A")
        case PokemonType.psychic.rawValue: return Color.init(hex: "FF1493")
        case PokemonType.rock.rawValue: return Color.init(hex: "BDB76B")
        case PokemonType.steel.rawValue: return Color.init(hex: "708090")
        case PokemonType.ice.rawValue: return Color.init(hex: "40E0D0")
        case PokemonType.ghost.rawValue: return Color.init(hex: "483D8B")
        case PokemonType.dragon.rawValue: return Color.init(hex: "8A2BE2")
        default: return Color.secondary
        }
    }
}

enum PokemonType : String {
    case grass, poison, fire, flying, water, bug, normal, electric, ground, fairy, fighting, psychic, rock, steel, ice, ghost, dragon
}
