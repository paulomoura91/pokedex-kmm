import Foundation
import shared

enum PokemonListState {
    case loading(Bool)
    case pokemonListItems([PokemonListItem])
    case error(String)
}
