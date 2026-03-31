Functions of the deck/blackjack:
  1. Drawing a single card randomly (popping top card)
  2. Showing each card (Printing)
  3. Comparing card values (comparing hand values)
  4. Shuffling a deck
  5. Duplication checks
  6. Deck size checks
  7. Initializing deck (Shuffle first)
  8. Resetting a deck
  9. Hand value logic (Aces)
  10. Check for bust (Greater than 21)
  11. Check for blackjack (natural 21)
  12. Deal initial hands
  13. Hit
  14. Stand
  15. Dealer Logic?
  16. Win/Loss Logic

Test Logic:
  1a. Check if a valid card.
  1b. Check if card value is not a card previously popped without shuffling a new deck (individual [value, suite]'s only seen once)
  2a. Checking whether it prints cards.
  3a. Check if the hand value translates to blackjack values (Face Card Values).
  3b. Check inputs are correct outputs. (Assert equals)
  4a. Check entire discard pile against new shuffle deck (comparing deck 1 vs deck 2)
  4b. Check shuffle logic created a new, unique, deck.
  
  
  

# Write test logic code here:
