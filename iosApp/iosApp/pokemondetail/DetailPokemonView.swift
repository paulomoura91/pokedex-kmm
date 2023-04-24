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
            HStack {
                ForEach((pokemon.types), id: \.self) { type in
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
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding(.all, 10)
    }
    
    private func getTypeColor(type: String) -> Color {
        switch(type.uppercased()) {
        case PokemonType.GRASS.rawValue: return Color.init(hex: "008000")
        case PokemonType.POISON.rawValue: return Color.init(hex: "800080")
        case PokemonType.FIRE.rawValue: return Color.init(hex: "FF0000")
        case PokemonType.FLYING.rawValue: return Color.init(hex: "6495ED")
        case PokemonType.WATER.rawValue: return Color.init(hex: "00BFFF")
        case PokemonType.BUG.rawValue: return Color.init(hex: "9ACD32")
        case PokemonType.NORMAL.rawValue: return Color.init(hex: "778899")
        case PokemonType.ELECTRIC.rawValue: return Color.init(hex: "FFD700")
        case PokemonType.GROUND.rawValue: return Color.init(hex: "DEB887")
        case PokemonType.FAIRY.rawValue: return Color.init(hex: "FF69B4")
        case PokemonType.FIGHTING.rawValue: return Color.init(hex: "A52A2A")
        case PokemonType.PSYCHIC.rawValue: return Color.init(hex: "FF1493")
        case PokemonType.ROCK.rawValue: return Color.init(hex: "BDB76B")
        case PokemonType.STEEL.rawValue: return Color.init(hex: "708090")
        case PokemonType.ICE.rawValue: return Color.init(hex: "40E0D0")
        case PokemonType.GHOST.rawValue: return Color.init(hex: "483D8B")
        case PokemonType.DRAGON.rawValue: return Color.init(hex: "8A2BE2")
        default: return Color.secondary
        }
    }
}

enum PokemonType : String {
    case GRASS, POISON, FIRE, FLYING, WATER, BUG, NORMAL, ELECTRIC, GROUND, FAIRY, FIGHTING, PSYCHIC, ROCK, STEEL, ICE, GHOST, DRAGON
}
