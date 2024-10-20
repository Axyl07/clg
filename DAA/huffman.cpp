#include <iostream>
#include <queue>
#include <unordered_map>
using namespace std;

// Node structure for the Huffman Tree
struct Node {
    char ch;
    int freq;
    Node *left, *right;

    Node(char ch, int freq, Node* left = nullptr, Node* right = nullptr) {
        this->ch = ch;
        this->freq = freq;
        this->left = left;
        this->right = right;
    }
};

// Comparison function for the min-heap (priority queue)
struct Compare {
    bool operator()(Node* a, Node* b) {
        return a->freq > b->freq;  // Min-heap: smallest frequency has higher priority
    }
};

// Recursive function to generate Huffman Codes
void generateCodes(Node* root, string code, unordered_map<char, string>& codes) {
    if (!root) return;

    // If leaf node, store the character and its code
    if (!root->left && !root->right) {
        codes[root->ch] = code;
    }

    generateCodes(root->left, code + "0", codes);
    generateCodes(root->right, code + "1", codes);
}

// Build the Huffman Tree from frequency map
Node* buildHuffmanTree(unordered_map<char, int>& freqMap) {
    priority_queue<Node*, vector<Node*>, Compare> minHeap;

    // Create a leaf node for each character and add to min-heap
    for (auto& pair : freqMap) {
        minHeap.push(new Node(pair.first, pair.second));
    }

    // Build the Huffman Tree
    while (minHeap.size() > 1) {
        Node* left = minHeap.top(); minHeap.pop();
        Node* right = minHeap.top(); minHeap.pop();

        // Create a new internal node with combined frequency
        int sum = left->freq + right->freq;
        minHeap.push(new Node('\0', sum, left, right));
    }

    return minHeap.top();  // Root of the Huffman Tree
}

// Function to print the Huffman Codes
void printHuffmanCodes(const unordered_map<char, string>& codes) {
    cout << "Huffman Codes:\n";
    for (const auto& pair : codes) {
        cout << pair.first << ": " << pair.second << "\n";
    }
}

// Driver code
int main() {
    // Input string from user
    string text;
    cout << "Enter the input string: ";
    getline(cin, text);

    // Build the frequency map
    unordered_map<char, int> freqMap;
    for (char ch : text) {
        freqMap[ch]++;
    }

    // Build the Huffman Tree
    Node* root = buildHuffmanTree(freqMap);

    // Generate Huffman Codes
    unordered_map<char, string> codes;
    generateCodes(root, "", codes);

    // Print the Huffman Codes
    printHuffmanCodes(codes);

    return 0;
}
