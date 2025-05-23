//
//  iosAppApp.swift
//  iosApp
//
//  Created by Gleb Kuznetsov on 02.05.2025.
//

import SwiftUI
import shared

@main
struct iosApp: App {
    
    init() {
        IosKoin.shared.initialize()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
