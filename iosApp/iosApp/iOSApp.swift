import SwiftUI
import ComposeApp

@main
struct iOSApp: App {


    init() {
        KoinIOS.shared.setup()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
