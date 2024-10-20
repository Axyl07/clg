#include <iostream>
using namespace std;

// Linear Search implementation
int linearSearch(int arr[], int n, int target) {
    for (int i = 0; i < n; i++) {
        if (arr[i] == target) {
            return i; // Return the index if the target is found
        }
    }
    return -1; // Return -1 if the target is not found
}

// Binary Search implementation
int binarySearch(int arr[], int low, int high, int target) {
    while (low <= high) {
        int mid = low + (high - low) / 2;

        if (arr[mid] == target) {
            return mid; // Return the index if the target is found
        }
        if (arr[mid] < target) {
            low = mid + 1; // Search the right half
        } else {
            high = mid - 1; // Search the left half
        }
    }
    return -1; // Return -1 if the target is not found
}

int main() {
    cout<<"Aryan Chauhan IT-B 086"<<endl;
    int n, target, choice;
    
    // Input array size
    cout << "Enter the number of elements: ";
    cin >> n;

    int arr[n];
    
    // Input array elements
    cout << "Enter the elements: ";
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    
    // Input target element to search for
    cout << "Enter the target element: ";
    cin >> target;
    
    // Choose the search algorithm
    cout << "Choose the search algorithm:\n";
    cout << "1. Linear Search\n";
    cout << "2. Binary Search (Array must be sorted)\n";
    cout << "Enter your choice: ";
    cin >> choice;

    int result = -1;

    switch (choice) {
        case 1:
            result = linearSearch(arr, n, target);
            break;
        case 2:
            // Make sure the array is sorted before using Binary Search
            // If the array is not sorted, Binary Search will not work correctly
            result = binarySearch(arr, 0, n - 1, target);
            break;
        default:
            cout << "Invalid choice!" << endl;
            return 1;
    }

    if (result != -1) {
        cout << "Element found at index: " << result << endl;
    } else {
        cout << "Element not found in the array." << endl;
    }

    return 0;
}
