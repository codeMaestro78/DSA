# Competitive-programming runner

Put a test case in `input.txt`, then run:

```bash
./cp-run.sh Codeforces/Q200B_Drinks.java
```

The runner detects the package from the source path, compiles the selected file
into `.cp-build/`, and executes it with `input.txt` as standard input.

Useful forms:

```bash
# Use another input file
./cp-run.sh Codeforces/Q200B_Drinks.java tests/drinks-1.in

# Type/paste input directly in the terminal
./cp-run.sh Codeforces/Q200B_Drinks.java --stdin

# Run every .in file in a folder
./cp-run.sh Codeforces/Q200B_Drinks.java --all tests/drinks
```

Make the script executable once with `chmod +x cp-run.sh` if needed.
