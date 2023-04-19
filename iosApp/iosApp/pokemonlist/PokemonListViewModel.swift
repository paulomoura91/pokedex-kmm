import shared
import RxSwift
import KMPNativeCoroutinesRxSwift

class PokemonsViewModel : ObservableObject {
    
    @Published var pokemons = [PokemonListItem]()
    private let useCase: GetPokemonsUseCase
    
    init(useCase: GetPokemonsUseCase = UseCasesHelper().getPokemonsUseCase) {
        
        self.useCase = useCase
        getPokemons()
    }
    
    private func getPokemons() {
        
        let observable = createObservable(for: useCase.getPokemons())
        
        let disposable = observable
            .observe(on: MainScheduler.instance)
            .subscribe(onNext: { value in
                switch(value) {
                case is ResponseLoading<NSArray>:
                    self.showLoading()
                case is ResponseSuccess<NSArray>:
                    self.pokemons = value.data as! [PokemonListItem]
                case is ResponseError<NSArray>:
                    self.showError()
                default:
                    break
                }
            }, onError: { error in
                print("Received error: \(error)")
            }, onCompleted: {
                print("Observable completed")
            }, onDisposed: {
                print("Observable disposed")
            })
    }
    
    private func showLoading() {
        
    }
    
    private func showError() {
        
    }
}