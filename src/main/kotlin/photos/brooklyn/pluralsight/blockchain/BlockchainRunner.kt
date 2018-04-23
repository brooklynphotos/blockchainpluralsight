package photos.brooklyn.pluralsight.blockchain

import photos.brooklyn.pluralsight.blockchain.model.Block
import photos.brooklyn.pluralsight.blockchain.model.BlockChain
import photos.brooklyn.pluralsight.blockchain.model.BlockChainImpl
import photos.brooklyn.pluralsight.blockchain.model.ClaimType
import java.util.*

class BlockchainRunner

fun main(args: Array<String>) {
    val chain: BlockChain = BlockChainImpl()
    val block1 = Block(0, "ABC123", 1000.00, Date(), "QWE123", 10000, ClaimType.TotalLoss);
    val block2 = Block(1, "VBG345", 2000.00, Date(), "JKH567", 20000, ClaimType.TotalLoss, block1)
    val block3 = Block(2, "XCF234", 3000.00, Date(), "DH23ED", 30000, ClaimType.TotalLoss, block2)
    val block4 = Block(3, "CBHD45", 4000.00, Date(), "DH34K6", 40000, ClaimType.TotalLoss, block3)
    val block5 = Block(4, "AJD345", 5000.00, Date(), "28FNF4", 50000, ClaimType.TotalLoss, block4)
    val block6 = Block(5, "QAX367", 6000.00, Date(), "FJK676", 60000, ClaimType.TotalLoss, block5)
    val block7 = Block(6, "CGO444", 7000.00, Date(), "LKU234", 70000, ClaimType.TotalLoss, block6)
    val block8 = Block(7, "PLO254", 8000.00, Date(), "VBN456", 80000, ClaimType.TotalLoss, block7)

    chain.acceptBlock(block1)
    chain.acceptBlock(block2)
    chain.acceptBlock(block3)
    chain.acceptBlock(block4)
    chain.acceptBlock(block5)
    chain.acceptBlock(block6)
    chain.acceptBlock(block7)
    chain.acceptBlock(block8)

    chain.verifyChain()

    println("");
    println("");

    block4.createdDate = GregorianCalendar(2017, 9, 20).time

    chain.verifyChain()

    println()
}