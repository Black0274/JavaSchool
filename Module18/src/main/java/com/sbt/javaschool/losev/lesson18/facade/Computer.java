package com.sbt.javaschool.losev.lesson18.facade;

public class Computer {
    private CPU cpu;
    private Memory memory;
    private HardDrive hardDrive;

    private static class CPU{
        private void freeze() {}
        private void jump(long position) {}
        private void execute() {}
    }

    private static class Memory{
        void load(long position, byte[] data) {}
    }

    private static class HardDrive{
        byte[] read(long position, long size) { return new byte[]{0}; }
    }

    Computer(){
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    private final long BOOT_ADDRESS = 10;
    private final long BOOT_SECTOR = 20;
    private final long SECTOR_SIZE = 4096;

    public void start(){
        cpu.freeze();
        memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE));
        cpu.jump(BOOT_ADDRESS);
        cpu.execute();
    }
}
