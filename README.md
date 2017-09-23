# Brilliant cut

## Problem

Your task is to cut the given raw material of gemstones in a way that provides the biggest possible profit. There will be three types of gemstones: diamonds, sapphires and rubies.

You get the input data [here](cuts.json).

For each gem types, the following information is provided:

‘cuts’ => ‘size’, the size of the cut
‘cuts’ => ‘value’, the value of the cut in question
’raw chunks’, the original size of the raw, un-cut chunks

Leftover chunks or fragments cannot be combined.
Leftover chunks are counted as waste and they are substracted from the resulting profit.

For example: a raw diamond chunk size 23 can be cut in the following ways:

| Cuts         | Value     | Waste | Profit |
|--------------|-----------|-------|--------|
| 17           | 25        | 6     | 19     |
| 11, 11       | 14+14=28  | 1     | 27     |
| 7, 7,        | 21        | 2     | 19     |
| 11, 7        | 21        | 5     | 16     |
| 11           | 14        | 12    | 2      |
| 7            | 7         | 16    | -9     |

Calculate largest profit that may result from cutting the gems.

[Task copied from Wunderdog website](http://wunder.dog/brilliant-cut)

## Solution

Largest profit of given raw gemstones is calculated in following way.

1. Each gem's raw chunk is converted into tree which represent every possible cut which can be done with a single chunk.
1. TreeTraversal go through tree nodes from leafs to root and pick most profitable path.
1. Add together all values from most profitable paths

(I hope I catch all the bugs with to get correct result)

## Instructions
Build with command "mvn clean install -DskipTests".

Run with command "java -jar target/brilliantcut.jar inputs.json"

### Tree representation

A raw diamond chunk size 23 with cut sizes: 7, 11 and 17 is converted into following [Tree](CutTree.png).
(png created in http://jimblackler.net/treefun/)

### Speed

Biggest speed pump was achieved when gem chunks with same size is calculated only once.

I wrote implementation with Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz which has 8 cores...

Kotlin at the moments does not have any collections which have parallel support.
Tried to increase speed by using parallel streams with java 8 collections
but they did not work as expected. (speed decreased!)

Another option is to use threads for example each gem could be handled inside own thread
or use thread in lower level like when building trees or in tree traversal.
But I don't like threads so I skip that one.

(Some kind of channel based tree creation and traversal could be cool but out of this project scope)