# AI Copilot Instructions - Java Concurrency Learning Project

## Project Overview
This is a progressive learning project on **Java multithreading and concurrency** (M09-RA2). Seven modules introduce threading concepts with increasing complexity: basic threads, thread lifecycle (join), sleep/delays, synchronization, and advanced patterns like wait/notify and the Dining Philosophers problem.

## Architecture & Threading Patterns

### Core Design Philosophy
- **Each module is self-contained**: No cross-module dependencies; each teaches isolated concepts
- **Main is the orchestrator**: Most modules use `main()` to spawn threads, use `join()` to wait, then report results
- **Thread-first modeling**: Nearly all active classes extend `Thread` and override `run()`

### Module Breakdown

| Module | Concept | Key Classes |
|--------|---------|-------------|
| 01-Fils | Basic threading | `Fil.extend Thread` - no sync |
| 02-Futbol | Thread.join() pattern | `Futbolista` - parallel execution, single main() |
| 03-Sleep_i_coet | Delays & state changes | `Motor` - infinite loop with `Thread.sleep()` |
| 04-Join | join() & long computations | `Treballador` - lifecycle simulation |
| 05-Sincronitzacio | Synchronized methods | `Compte` - singleton + synchronized accessors |
| 06-Espera_wait | wait/notify pattern | `Esdeveniment.ferReserva/cancelaReserva()` - condition loops |
| 07-Filosofs | Dining Philosophers | `Taula`, `Forquilla` - incomplete, ready for deadlock-free solution |

## Critical Patterns

### Thread Spawning Pattern (01-02, 04)
```java
// Create threads in array
for (int i = 0; i < n; i++) threads[i] = new ThreadClass(...);

// Start all
for (Thread t : threads) t.start();

// Wait for completion (join blocks until thread ends)
for (Thread t : threads) t.join();

// Process results
for (ThreadClass t : threads) System.out.println(t.result);
```

### Synchronized Access Pattern (05-06)
```java
// Singleton with synchronized factory
public static synchronized T getInstance() {
    if (instance == null) instance = new T();
    return instance;
}

// Synchronized mutators
public synchronized void setState(int val) { 
    this.state = val;  // Prevents concurrent modification
}

// Wait/notify pattern (06)
public synchronized void reservar() {
    while (!canProceed()) wait();  // Loop until condition true
    // critical section
    notifyAll();  // Wake waiting threads
}
```

### Infinite Loop with Escape (03)
```java
while (true) {
    // do work
    if (shouldStop) break;  // Exit condition
    Thread.sleep(delay);
}
```

## Development Workflows & Commands

### Build & Compile
- No build tool (Maven/Gradle) - use javac directly or IDE compilation
- Each module has `src/` subdirectory (02-07) or root level (01)
- No external dependencies - pure Java stdlib only

### Run Individual Main Classes
```bash
# From within module directory
javac src/*.java
java -cp src ClassName  # Verify main() exists in target class
```

### Common Debugging Scenarios
- **Race conditions**: Add `System.out.printf()` with thread name to track interleaving
- **Deadlock (07)**: Watch output for stuck threads; solution requires Dijkstra's asymmetric fork pattern
- **Synchronization bugs**: Check `synchronized` keyword on all shared-state accessors (methods & static factory)
- **join() timeout**: Some exercises may require timeout logic `join(long millis)`

## Project-Specific Conventions

### Naming & Localization
- **Catalan/Spanish mixed**: Class names are English (`Compte`, `Assistent`), comments often Spanish ("//SI HAY SLEEP TIME")
- **Naming convention**: PascalCase for classes (Java standard), lowerCamelCase for methods, `nom`/`saldo`/`edat` for Catalan-influenced variables
- **Static fields track test parameters**: `Organitzador.placesDisponibles`, `Taula.forquilles` - modify to change test scale

### Synchronization Essentials
1. **Synchronized factory methods** - always check `getInstance()` has `synchronized`
2. **All mutations require sync** - if method modifies instance state, it must be `synchronized`
3. **wait() requires synchronized context** - can only call from inside `synchronized` method/block
4. **Condition loops not if** - use `while (condition) wait()` not `if`, as spurious wakeups are possible

### Incomplete/In-Progress Code
- **07-Filosofs/Taula.java**: Skeleton only - Forquilla is minimal. Expects student to implement:
  - Philosopher threads (Comensal or similar)
  - Fork allocation logic with deadlock prevention (e.g., asymmetric fork ordering by ID)
  - Main loop with think/eat states

## Cross-Component Communication
- **No callbacks/listeners** - threads are independent, communicate via shared state only
- **No queues** - direct method calls (synchronized) for coordination
- **No thread pools** - manual thread creation/management only

## Critical File References
- [01-Fils/Fil.java](01-Fils/Fil.java) - template for `extends Thread` and `run()` override
- [02-Futbol/src/Futbolista.java](02-Futbol/src/Futbolista.java) - shows main() orchestration + join() pattern
- [05-Sincronitzacio/src/Compte.java](05-Sincronitzacio/src/Compte.java) - singleton + synchronized template
- [06-Espera_wait/src/Esdeveniment.java](06-Espera_wait/src/Esdeveniment.java) - wait/notify + condition loops
- [07-Filosofs/src/Forquilla.java](07-Filosofs/src/Forquilla.java) - incomplete; start here for Dining Philosophers solution

## When Modifying Code

### Thread Class Changes
- Always ensure `run()` is overridden, not `main()`
- Use `super(name)` to set thread name for logging
- Check for proper `join()` usage if refactoring main classes

### Synchronization Changes
- Add `synchronized` to ALL methods accessing instance state (not just critical sections - Java sync is method-level)
- If adding `wait()`, verify it's in a loop checking a boolean flag, never bare `if`
- Always pair `wait()` with `notifyAll()` (not `notify()` - safer for multiple waiters)

### Testing New Code
- Run Main classes directly; verify all threads complete and print results
- Check console output matches expected interleaving (or lack thereof for synchronized sections)
- For modules 01-04, output should show all threads executing; for 05-06, verify serialized access

---
*Last updated: 2026-02-06 for Java Concurrency (M09-RA2) progressive learning project*
