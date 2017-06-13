package ru.pattern.structural.adapter.example1;

import java.util.Random;

public class AdapterClassDemo {
    public static void main(String[] args) {
        SequenceGenerator generator;
        generator = new SequenceGenerator(new StandartGenerator());
        //generator = new SequenceGenerator(new RandomGeneratorAdapterForClass());
        //generator = new SequenceGenerator(new RandomGeneratorAdapterForObject(new RandomGenerator()));


        int[] var2 = generator.generate(10);
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int i = var2[var4];
            System.out.print(i + " ");
        }
    }
}

interface TypeGenerator {
    int next();
}

class SequenceGenerator {
    private TypeGenerator generator;

    public SequenceGenerator(TypeGenerator generator) {
        this.generator = generator;
    }

    public int[] generate(int length) {
        int[] ret = new int[length];

        for(int i = 0; i < length; ++i) {
            ret[i] = this.generator.next();
        }

        return ret;
    }
}

class StandartGenerator implements TypeGenerator {
    public int next() {
        return 1;
    }
}

class RandomGenerator {
    public int getRandomNumber() {
        return (new Random()).nextInt(10);
    }
}

class RandomGeneratorAdapterForClass extends RandomGenerator implements TypeGenerator {
    public int next() {
        return super.getRandomNumber();
    }
}

class RandomGeneratorAdapterForObject implements TypeGenerator {
    RandomGenerator random;

    public RandomGeneratorAdapterForObject(RandomGenerator random) {
        this.random = random;
    }

    public int next() {
        return this.random.getRandomNumber();
    }
}