package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource.dessertList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel: ViewModel() {
    //App UI State
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun onDessertClicked() {
        _uiState.update {currentState ->
            val dessertsSold = currentState.dessertsSold + 1
            val nextDessertIndex = determineDessertIndex(dessertsSold)

            currentState.copy(
                currentDessertIndex = nextDessertIndex,
                revenue = currentState.revenue.plus(currentState.currentDessertPrice),
                dessertsSold = dessertsSold,
                currentDessertImageId = dessertList[nextDessertIndex].imageId,
                currentDessertPrice = dessertList[nextDessertIndex].price
            )
        }
    }

    private fun determineDessertIndex(dessertsSold: Int): Int {
        var dessertIndex = 0

        for(index in dessertList.indices) {
            if(dessertsSold >= dessertList[index].startProductionAmount){
                dessertIndex = index
            } else {
                // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
                // you'll start producing more expensive desserts as determined by startProductionAmount
                // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
                // than the amount sold.
                break
            }
        }
        return dessertIndex
    }

    fun updateRevenue() {
        _uiState.update { currentState ->
            currentState.copy(
                revenue = currentState.revenue.plus(currentState.currentDessertPrice),
                dessertsSold = currentState.dessertsSold.inc()
            )
        }
    }

    fun updateDessertPriceAndImage(newPrice: Int, newImageId: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                currentDessertImageId = newImageId,
                currentDessertPrice = newPrice
            )
        }
    }
}