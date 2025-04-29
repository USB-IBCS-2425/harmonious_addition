from Button import*
from Cell import*

class OBoard():

    def __init__(self, win):
        self.cells = []
        for i in range(8):
            for j in range(8):
                if (i+j)%2 == 0:
                    color = "white"
                else:
                    color = "grey"
                self.cells.append(Cell(win, (j, i), color))

        self.potentMoves = []
        self.whoMove = "black"

    def setWhoMove(self, pl):
        self.whoMove = pl

    def getPotentMoves(self):
        return self.potentMoves

    def getAllCells(self):
        return self.cells

    def checkMoves(self, highlight=True):
        moves = []
        UL = [-1, -8]
        U = [0, -8]
        UR = [1, -8]
        L = [-1, 0]
        R = [1, 0]
        DL = [-1, 8]
        D = [0, 8]
        DR = [1, 8]
        directions = [UL, U, UR, L, R, DL, D, DR]
        for i in range(64):
            cell = self.cells[i]
            if cell.piece != "":
                if self.whoMove != cell.piece:
                    for d in directions:
                        if (i%8 != 0) or (d[0] == 0):
                            if (i%8 != 7) or (d[0] == 0):
                                newInd = i + d[0] + d[1]

                                if 0 <= newInd < 64:
                                    if self.cells[newInd].piece == "":
                                        
                                        checkForValid = False
                                        pos = i
                                        for j in range(6):
                                            
                                            pos = pos - d[0] - d[1]
                                            
                                            
                                            if (63 < pos):
                                                break
                                            elif (pos < 0):
                                                break
                                            elif self.cells[pos].piece == "":
                                                break
                                            elif self.cells[pos].piece == self.whoMove:
                                                checkForValid = True
                                                
                                                break
                                            elif (pos%8 == 0) and (d[0] != 0):
                                                break
                                            elif (pos%8 == 7) and (d[1] != 0):
                                                break
                                        if checkForValid:
                                            moves.append(self.cells[newInd])
        if highlight:
            for m in moves:
                m.highlight()

        return moves

    def place(self, c):
        c.updatePiece(self.whoMove)
        toFlip = []
        ind = self.cells.index(c)
        UL = [-1, -8]
        U = [0, -8]
        UR = [1, -8]
        L = [-1, 0]
        R = [1, 0]
        DL = [-1, 8]
        D = [0, 8]
        DR = [1, 8]
        directions = [UL, U, UR, L, R, DL, D, DR]
        for d in directions:
            newInd = ind + d[0] + d[1]
            tempFlip = []
            for i in range(7):
                if (newInd%8 == 7) and (d[0] == -1):
                    break
                if (newInd%8 == 0) and (d[0] == 1):
                    break
                if 0 <= newInd < 64:
                    if (self.cells[newInd].piece != self.whoMove) and (self.cells[newInd].piece != ""):
                        tempFlip.append(newInd)
                    elif (self.cells[newInd].piece == self.whoMove):
                        for t in tempFlip:
                            toFlip.append(t)
                        break
                    else:
                        break
                    newInd = newInd + d[0] + d[1]

        if self.whoMove == "black":
            change = "white"
        else:
            change = "black"
        for f in toFlip:
            #print(f)
            self.cells[f].updatePiece(self.whoMove)
        self.whoMove = change
    def isOver(self):
        for c in self.cells:
            if c.piece == "":
                return False
        return True
    def changeT(self):
        if self.whoMove == "black":
            self.whoMove = "white"
        else:
            self.whoMove = "black"
    def calcScore(self):
        b = 0
        w = 0
        for c in self.cells:
            if c.piece == "black":
                b+=1
            if c.piece == "white":
                w+=1
        return "black: " + str(b) + " -- white: " + str(w)
        
    def findBestMove(self, a):
        bestMove = None
        bestScore = -100000

        piecenum =0
        for c in self.cells:
            if c.piece != "":
                piecenum += 1

        
        for i in a:
            pos = self.cells.index(i)
            score = 0
            if pos == 0 or pos == 7 or pos == 56 or pos == 63:
                bestMove = i
                return bestMove
            
            if pos > 2 and pos < 5:
                score += 200

            if pos > 58 and pos < 61:
                score += 200

            if pos % 8 == 0:
                score += 200

            if pos % 8 == 7:
                score += 200

            if pos == 1 or pos == 6 or pos == 8 or pos == 9 or pos == 14 or pos == 15 or pos == 48 or pos == 49 or pos == 55 or pos == 54 or pos == 57 or pos == 62:
                score -= 800

            if pos % 8 == 1 or pos % 8 == 7 or pos > 8 and pos < 15 or pos > 48 and pos < 55:
                score -= 10

            flipCount = self.countFlips(i)
            if piecenum < 20:
                score += flipCount * 1

            elif piecenum > 50:
                score += flipCount * 10

            else:
                score += flipCount * 5

            originalState = [(cell.piece) for cell in self.cells]
            originalPlayer = self.whoMove

            self.simPlace(i)  
            oMoves = self.checkMoves(highlight=False) 
            oMoveCount = len(oMoves)

            for o in oMoves:
                oInd = self.cells.index(o)
                if oInd == 0 or oInd == 7 or oInd == 63 or oInd == 56:
                    score -= 1000
                    break
                if oInd % 8 == 0 or oInd % 8 == 7 or oInd in range(8, 16) or oInd in range(48, 56):
                    score -= 50

            if piecenum < 20:
                score -= oMoveCount * 25
            elif piecenum > 50:
                score -= oMoveCount * 5
            else:
                score -= oMoveCount * 10

        
            for ind, piece in enumerate(originalState):
                self.cells[ind].piece = piece
            self.whoMove = originalPlayer
            if score > bestScore:
                bestScore = score
                bestMove = i
        print(bestScore)
        return bestMove

    def countFlips(self, c):
        flipCount = 0
        ind = self.cells.index(c)
        UL = [-1, -8]
        U = [0, -8]
        UR = [1, -8]
        L = [-1, 0]
        R = [1, 0]
        DL = [-1, 8]
        D = [0, 8]
        DR = [1, 8]
        directions = [UL, U, UR, L, R, DL, D, DR]
        for d in directions:
            newInd = ind + d[0] + d[1]
            tempFlip = 0
            for i in range(7):
                if (newInd % 8 == 7) and (d[0] == -1):
                    break
                if (newInd % 8 == 0) and (d[0] == 1):
                    break
                if 0 <= newInd < 64:
                    if (self.cells[newInd].piece != self.whoMove) and (self.cells[newInd].piece != ""):
                        tempFlip += 1
                    elif (self.cells[newInd].piece == self.whoMove):
                        flipCount += tempFlip
                        break
                    else:
                        break
                    newInd = newInd + d[0] + d[1]
        return flipCount

    def simPlace(self, c):
        c.piece = self.whoMove
        ind = self.cells.index(c)
        UL = [-1, -8]
        U = [0, -8]
        UR = [1, -8]
        L = [-1, 0]
        R = [1, 0]
        DL = [-1, 8]
        D = [0, 8]
        DR = [1, 8]
        directions = [UL, U, UR, L, R, DL, D, DR]
        for d in directions:
            newInd = ind + d[0] + d[1]
            tempFlip = []
            for i in range(7):
                if (newInd % 8 == 7) and (d[0] == -1):
                    break
                if (newInd % 8 == 0) and (d[0] == 1):
                    break
                if 0 <= newInd < 64:
                    if (self.cells[newInd].piece != self.whoMove) and (self.cells[newInd].piece != ""):
                        tempFlip.append(newInd)
                    elif (self.cells[newInd].piece == self.whoMove):
                        for t in tempFlip:
                            self.cells[t].piece = self.whoMove
                        break
                    else:
                        break
                    newInd = newInd + d[0] + d[1]
        if self.whoMove == "black":
            change = "white"
        else:
            change = "black"

            
            


