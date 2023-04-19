import SwiftUI

struct ErrorView: View {
    
    private let errorMessage: String
    
    init(errorMessage: String) {
        self.errorMessage = errorMessage
    }
    
    var body: some View {
        VStack {
            Text("An error has occurred!")
                .font(.title)
                .padding(.bottom)
            Text(errorMessage)
                .font(.headline)
            Spacer()
        }
        .padding()
        .background(.thinMaterial)
        .cornerRadius(16)
    }
}
