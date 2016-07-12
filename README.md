PokeServerSim
=============

Out of curiosity, I wanted to explore what it might be like to create a mostly-stateless
procedurally generated world for spawning Pokemon.

The Player features are as follows:

1. Players should feel like Pokemon are generated randomly as they move about the world.
2. Players expect to see a distribution of Pokemon rarity (Common -> Rare).
3. Players should see region relevant Pokemon.
4. Players should see the Pokemon others see, as to encourage teamwork and discovery.

The Technical constraints (for fun) are as follows:

1. Regions not seen should not have (substantial) calculations performed
2. Generating Pokemon should be stateless. No information about previous spawns/timers are needed.
3. Caching results of generated Pokemon is OK

Currently
=========

Basic simulation is in place, with "Players" moving around and triggering generation lookups. As player's see
Pokemon, they are added to the player. Generation lookups are stateless, and only require the player's position
and the server's current time to make a decision to generate a Pokemon or not.

    Example Output:

    Found one! 217 367
    Found one! 0 796
    Found one! 21 20
    Found one! 263 336
    Found one! 757 594
    Found one! 753 605
    Found one! 796 15
    Found one! 754 598
    Found one! 744 619
    Found one! 757 0
    Player -2818048 caught 1
    4 LEAFMON captured.
    Player -16774693 caught 1
    2 FIREMON captured.
    Player -13703129 caught 3
    1 WATERMON captured.
    1 FIREMON captured.
    2 LEAFMON captured.

As shown above, player's capture differing amounts and kinds of Pokemon, despite a fixed distribution for spawning.

Interesting Bits
================

Visualizing the Algorithm
--------------------------
The most interesting part is in `Generator.generate(...)`. This function is the entire algorithm for deciding to
generate a Pokemon or not.

I enjoyed trying to visualize how this would look, and I came up with a rough approximation:

Imagine that each point in space is represented with a stick, and along the stick are tick marks equally spaced, by an
amount of `interval`. As time moves along this stick, a Pokemon is generated whenever we pass a tick mark. Given no other
inputs, its trivial to see that every point in space would generate the same Pokemon, at the same time, in an obvious
pattern (defined by the interval length). To "start" at different positions along the stick, we hash both the position
and time. This ensures that as a player moves, they don't see the distribution from a statically defined world. It
"appears" that Pokemon are generated randomly along their journey.

This still suffers from an obvious flaw: A player sitting in one location will see the distribution, and it won't appear
random. For this, we have other solutions that will mitigate it further.

Controllable Variables
----------------------
To combat this, we have several controllable levers: sight radius, location fidelity, and interval periodicity.

**Sight Radius** affects the number of points we look at for determining if a Pokemon should spawn there. A sight-radius
of "one" means our current coordinate. Increasing the sight radius necessarily increases the number of Pokemon seen

**Location Fidelity** determines the density of points to consider for a given player. Increasing the density will mean
more points to look up for a given sight radius, and will increase the spawn rate.

**Interval Periodicity** determines how often we hit a tick mark over time. Decreasing this value will increase the spawn
rate.

**Interval Length** determines how long a Pokemon will remain seeable by other players, and how long a player has to react
before it disappears. Increasing this value draws more players to a given area, and will increase the average number of
Pokemon available at any given point.

And many more.