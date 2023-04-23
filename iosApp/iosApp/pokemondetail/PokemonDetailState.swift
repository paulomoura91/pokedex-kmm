import Foundation
import shared

enum PokemonDetailState {
    case loading(Bool)
    case pokemon(Pokemon)
    case error(String)
}
